

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCachelmpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub


		IAlgoCache<Long, DataModel<String>> algo = new LRUAlgoCachelmpl<>(5);
		IDao<Long, DataModel<String>> dao = new DaoFileImpl<>("src/main/resources/dataFile.txt");
		CacheUnit<String> cacheUnit = new CacheUnit<>(algo, dao);


		Long[] ids = {(long)1,(long)2,(long)3,(long)4,(long)5,(long)6};
		DataModel<String> data1 = new DataModel<String>((long)1, "11");
		DataModel<String> data2 = new DataModel<String>((long)2, "62");
		DataModel<String> data3 = new DataModel<String>((long)3, "53");
		DataModel<String> data4 = new DataModel<String>((long)4, "44");
		DataModel<String> data5 = new DataModel<String>((long)5, "35");
		DataModel<String> data6 = new DataModel<String>((long)6, "26");

		DataModel<String>[] datas = new DataModel[6]; 

		datas[0] = data1;
		datas[1] = data2;
		datas[2] = data3;
		datas[3] = data4;
		datas[4] = data5;
		datas[5] = data6;

		cacheUnit.putDataModels(datas);

		DataModel<String>[] finalDatas = new DataModel[6]; 

		finalDatas = cacheUnit.getDataModels(ids);
		for (int i = 0; i < finalDatas.length; i++) {
			System.out.println(datas[i].getDataModelContent());

		}

		
	}
}


