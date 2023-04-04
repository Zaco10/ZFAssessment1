package org.assessment;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;



public class ProcessAddress {

    public static void main(String[] args) throws FileNotFoundException {

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("C:\\addresses.json");
        Address addressObj = new Address();

        //Read all the objects from the JSON file
        MappingIterator<Address> iterator = null;
        try {
            iterator = objectMapper.readerFor(Address.class).readValues(inputStream);
            List<Address> listOfAddresses = iterator.readAll();

            //a. prettyPrint an address
           // addressObj.prettyPrintAddress(listOfAddresses.get(2));

            //b. pretty print all the addresses in the attached file
            //String st = addressObj.prettyPrintAllAddresses(listOfAddresses);
            //System.out.println(st);

            //c. print an address of certain type
            //String st = addressObj.printCertainTypeAddress(listOfAddresses,"Business");
            //System.out.println(st);

            //d. validate an Address
            //boolean bool = addressObj.isAddressValid(listOfAddresses.get(2));
            //System.out.println(bool);

            //e. validate all addresses with messages
            String vm = addressObj.validateAllAddresses(listOfAddresses);
            System.out.println(vm);


        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }


}

