# ZFAssessment
This is a maven project that has few methods to manipulate a given address/addresses

The application receives a JSON file containing 3 different addresses in a JSON formamat, I then map those addresses 
to My address Java class and manipulate the received the data using the methods that are also found on the same class.

List of Methods:

 //a. PrettyPrint an address details - city - province/state - postal code – country
 public String prettyPrintAddress(Address address
    
    
 //b. pretty print all addresses
 public String prettyPrintAllAddresses(List<Address> addressList) 
    

  //c. Print address based on specified type
    public String printCertainTypeAddress(List<Address> addressList, String addressType) 
     

  //d. //Validate a specified address
    public boolean isAddressValid(Address address) 

  // e. //Validate all given addresses
    public String validateAllAddresses(List<Address> addressList) 



