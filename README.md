# MMU

Management memory unit
A memory management system that simulates the page fault process.
The customer forwards the request via the server and the CLI.
The CLI transferred to the Handel Request class, which extracts the information from the Json file.
The Handel Request transfers it to the controller who analyzes the requests and transfers it to the Service class.
The service performs the operation accordingly - if necessary it turns to Cash Unit and retrieves from the secondary memory
or alternatively retrieves the information from the main memory.

