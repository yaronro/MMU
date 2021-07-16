package com.hit.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import com.google.gson.Gson.*;

import com.hit.driver.CacheMemorySummery;
import com.hit.driver.DataModel;
import com.hit.driver.Request;
import com.hit.driver.Response;
import com.hit.model.messages.SetTextInfoModelMessage;
import com.hit.model.messages.ShowInfoDialogModelMessage;
import com.hit.view.messages.ShowStatisticsViewMessage;
import com.hit.view.messages.UploadFileViewMessage;

public class CacheUnitModel extends Observable implements Model {

    private CacheUnitClient cuc = new CacheUnitClient();
    private Gson gson = new Gson();

    public final static String ACTION = "action";
    public static final String STATUS = "STATUS";
    public static final String GET_SUCCESS = "GET_SUCCESS";
    public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
    public static final String DELETE_SUCCESS = "DELETE_SUCCESS";
    public static final String GET_CACHE_MEMORY = "GET_CACHE_MEMORY";
    public static final String GET_CACHE_MEMORY_SUCCESS = "GET_CACHE_MEMORY_SUCCESS";

    @Override
    public <T> void updateModelData(T t) {
        if (t instanceof ShowStatisticsViewMessage) {
            Map<String, String> headers = new HashMap<>();
            headers.put(ACTION, GET_CACHE_MEMORY);
            @SuppressWarnings("unchecked")
			Request<DataModel<String>[]> emptyStringGetCacheMemoryRequest = new Request<>(headers, new DataModel[0]);
            String stringRequest = gson.toJson(emptyStringGetCacheMemoryRequest);
            handleStringRequest(stringRequest);
        } else if (t instanceof UploadFileViewMessage) {
            UploadFileViewMessage uploadFileViewMessage = (UploadFileViewMessage) t;
            readFileAndSendRequest(uploadFileViewMessage.getFile());
        } else {
            try {
                cuc.send(t.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void readFileAndSendRequest(File file) {
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            String fileStringRequest = new String(fileBytes);
            handleStringRequest(fileStringRequest);
        } catch (IOException e) {
            e.printStackTrace();
            updateChangeForController(new ShowInfoDialogModelMessage("Can't read file"));
        }
    }

    private void handleStringRequest(String stringRequest) {
        String responseString = "No Response";
        try
        {
            responseString = cuc.send(stringRequest);
            getDisplayResponseFromString(responseString);
        } catch (IOException e) {
            e.printStackTrace();
            updateChangeForController(new ShowInfoDialogModelMessage(responseString));
        }
    }

    private String getResponseDisplayString(Response<DataModel<String>[]> response) {
        StringBuilder stringBuilder = new StringBuilder();
        DataModel<String>[] body = response.getBody();
        for (DataModel<String> dataModel : body) {
            if (dataModel != null) {
                stringBuilder
                        .append(dataModel.getDataModelId())
                        .append(" - ")
                        .append(dataModel.getDataModelContent())
                        .append('\n');
            }
        }
        return stringBuilder.toString();
    }

    private void getDisplayResponseFromString(String responseString) {
        Type objectTypeRef = new TypeToken<Response<JsonElement>>() {
        }.getType();
        // set the requset Obj
        Response<JsonElement> response = gson.fromJson(responseString, objectTypeRef);
        switch (getStatusHeader(response.getHeaders())) {
            case UPDATE_SUCCESS: {
                updateChangeForController(new ShowInfoDialogModelMessage("Update Success"));
            }
            break;
            case DELETE_SUCCESS: {
                updateChangeForController(new ShowInfoDialogModelMessage("Delete Success"));
            }
            break;
            case GET_SUCCESS: {
                Type dataModelTypeRef = new TypeToken<Response<DataModel<String>[]>>() {
                }.getType();
                String displayResponseString = getResponseDisplayString(gson.fromJson(responseString, dataModelTypeRef));
                updateChangeForController(new SetTextInfoModelMessage(displayResponseString));
            }
            break;
            case GET_CACHE_MEMORY_SUCCESS: {
                Type cacheMemorySummeryType = new TypeToken<Response<CacheMemorySummery>>() {
                }.getType();
                String displayResponseString = cacheMemoryDisplayResponseString(gson.fromJson(responseString, cacheMemorySummeryType));
                updateChangeForController(new SetTextInfoModelMessage(displayResponseString));
            }
        }

    }

    private String cacheMemoryDisplayResponseString(Response<CacheMemorySummery> cacheMemorySummeryResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        CacheMemorySummery cacheMemorySummery = cacheMemorySummeryResponse.getBody();

        stringBuilder.append("Available: " ).append(cacheMemorySummery.getAvailable()).append('\n');
        stringBuilder.append("Capacity: " ).append(cacheMemorySummery.getCapacity()).append('\n');
        stringBuilder.append("Number Of Action: " ).append(cacheMemorySummery.getNumberOfAction()).append('\n');
        stringBuilder.append("Put and Remove: " ).append(cacheMemorySummery.getPutAndRemove()).append('\n');

        return stringBuilder.toString();
    }

    private void updateChangeForController(Object o) {
        setChanged();
        notifyObservers(o);
    }

    private String getStatusHeader(Map<String, String> headers) {
        return headers.get(STATUS);
    }
}
