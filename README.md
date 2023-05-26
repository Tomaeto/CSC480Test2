# CSC480Test2
CSC 480 Test 2 - Book Reorder program

Simulates a program for obtaining book publication data from a database, displaying and reordering in a GUI frame as well as an HTML page. Obtains data from an SQL database hosted on a Mercer University cloud server, stores the data in bean objects, builds and displays a selection table with the capacity to select rows or reorder based on different criteria. For the HTML page, the program uses the Jackson API to convert the bean objects into JSON objects and uses JavaScript to load the data into an HTML table.

File structure:

  src - contains source code files
  
  src/datamanage - contains Java code for accessing the database, the PublisherBean data wrapper, and the PubManager for organizing the data into PublisherBean objects
    
  src/gui - contains Java code for building the Java GUI containing the selection/reorder table
    
  src/json - contains Java code for converting PublisherBean data into JSON format using the Jackson API
    
  html - contains two HTML version of the table, one that is hardcoded and one that uses Javascript to access the JSON file and organize into the table
   
  data - contains image files for the sample company logos and publisher photos and the JSON conversion of the data obtained from the database
