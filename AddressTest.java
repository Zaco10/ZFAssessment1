package org.assessment;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddressTest extends TestCase {

    public void testPrettyPrintAddress() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("C:\\addresses.json");
        Address addressObj = new Address();

        MappingIterator<Address> iterator = null;
            iterator = objectMapper.readerFor(Address.class).readValues(inputStream);
            List<Address> listOfAddresses = iterator.readAll();

        addressObj.prettyPrintAddress(listOfAddresses.get(2));

        String expectedOutput = "Address 3\n" +
                "City 3\n" +
                "3456\n" +
                "South Africa\n"+
                "....................\n";
       String actualOutput = addressObj.prettyPrintAddress(listOfAddresses.get(2));
       assertEquals(expectedOutput, actualOutput);
    }


    public void testPrettyPrintAllAddresses() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("C:\\addresses.json");
        Address addressObj = new Address();

        MappingIterator<Address> iterator = null;
        iterator = objectMapper.readerFor(Address.class).readValues(inputStream);
        List<Address> listOfAddresses = iterator.readAll();

        addressObj.prettyPrintAllAddresses(listOfAddresses);

        String expectedOutput = "Address 1\n" +
                "Line 2\n" +
                "City 1\n" +
                "Eastern Cape\n" +
                "1234\n" +
                "South Africa\n" +
                "....................\n" +
                "City 2\n" +
                "2345\n" +
                "Lebanon\n" +
                "....................\n" +
                "Address 3\n" +
                "City 3\n" +
                "3456\n" +
                "South Africa\n" +
                "....................\n";
        String actualOutput = addressObj.prettyPrintAllAddresses(listOfAddresses);
        assertEquals(expectedOutput, actualOutput);
    }

    public void testPrintCertainTypeAddress() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("C:\\addresses.json");
        Address addressObj = new Address();

        MappingIterator<Address> iterator = null;
        iterator = objectMapper.readerFor(Address.class).readValues(inputStream);
        List<Address> listOfAddresses = iterator.readAll();

        addressObj.printCertainTypeAddress(listOfAddresses,"Business");

        String expectedOutput = "Address 3\n" +
                "City 3\n" +
                "3456\n" +
                "South Africa\n" +
                "....................\n";
        String actualOutput = addressObj.printCertainTypeAddress(listOfAddresses,"Business");
        assertEquals(expectedOutput, actualOutput);

    }

    public void testIsAddressValid() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("C:\\addresses.json");
        Address addressObj = new Address();

        MappingIterator<Address> iterator = null;
        iterator = objectMapper.readerFor(Address.class).readValues(inputStream);
        List<Address> listOfAddresses = iterator.readAll();

        boolean expectedOutput = true;
        boolean actualOutput = addressObj.isAddressValid(listOfAddresses.get(0));
        assertEquals(expectedOutput, actualOutput);
    }

    public void testValidateAllAddresses() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("C:\\addresses.json");
        Address addressObj = new Address();

        MappingIterator<Address> iterator = null;
        iterator = objectMapper.readerFor(Address.class).readValues(inputStream);
        List<Address> listOfAddresses = iterator.readAll();

        String expectedOutput = "Validating address 1\n" +
                "\n" +
                "--------------------------------\n" +
                "Validating address 2\n" +
                "Address Line cannot be empty\n" +
                "\n" +
                "--------------------------------\n" +
                "Validating address 3\n" +
                "Country with code ZA must have a province\n" +
                "\n" +
                "--------------------------------\n";
        String actualOutput = addressObj.validateAllAddresses(listOfAddresses);
        assertEquals(expectedOutput, actualOutput);

    }

}