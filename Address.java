package org.assessment;


import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author isaacmh
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    private String id;
    private AddressType type;
    private AddressLineDetail addressLineDetail;
    private ProvinceOrState provinceOrState;
    private String cityOrTown;
    private Country country;
    private String postalCode;
    private String suburbOrDistrict;
    private Date lastUpdated;

    //Physical Addr
   /* public Address(String id, AddressType addressType, AddressLineDetail addressLineDetail, ProvinceOrState provinceOrState, String cityOrTown, Country country, String postalCode, Date lastUpdated) {
        this.id = id;
        this.addressType = addressType;
        this.addressLineDetail = addressLineDetail;
        this.provinceOrState = provinceOrState;
        this.cityOrTown = cityOrTown;
        this.country = country;
        this.postalCode = postalCode;
        this.lastUpdated = lastUpdated;
    }*/
//
//    //Postal Addr
//    public Address(String id, AddressType addressType, String cityOrTown, Country country, String postalCode, Date lastUpdated) {
//        this.id = id;
//        this.addressType = addressType;
//        this.cityOrTown = cityOrTown;
//        this.country = country;
//        this.postalCode = postalCode;
//        this.lastUpdated = lastUpdated;
//    }
//
//    //Business Addr
//    public Address(String id, AddressType addressType, AddressLineDetail addressLineDetail, String cityOrTown, Country country, String postalCode, String suburbOrDistrict, Date lastUpdated) {
//        this.id = id;
//        this.addressType = addressType;
//        this.addressLineDetail = addressLineDetail;
//        this.cityOrTown = cityOrTown;
//        this.country = country;
//        this.postalCode = postalCode;
//        this.suburbOrDistrict = suburbOrDistrict;
//        this.lastUpdated = lastUpdated;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public AddressLineDetail getAddressLineDetail() {
        return addressLineDetail;
    }

    public void setAddressLineDetail(AddressLineDetail addressLineDetail) {
        this.addressLineDetail = addressLineDetail;
    }

    public ProvinceOrState getProvinceOrState() {
        return provinceOrState;
    }

    public void setProvinceOrState(ProvinceOrState provinceOrState) {
        this.provinceOrState = provinceOrState;
    }

    public String getCityOrTown() {
        return cityOrTown;
    }

    public void setCityOrTown(String cityOrTown) {
        this.cityOrTown = cityOrTown;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSuburbOrDistrict() {
        return suburbOrDistrict;
    }

    public void setSuburbOrDistrict(String suburbOrDistrict) {
        this.suburbOrDistrict = suburbOrDistrict;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    //a. PrettyPrint an address details - city - province/state - postal code – country
    public String prettyPrintAddress(Address address) {

        StringBuilder sb = new StringBuilder();
        if (address.getAddressLineDetail() != null) {
            if (!address.getAddressLineDetail().getLine1().isEmpty())
                sb.append(address.getAddressLineDetail().getLine1()).append("\n");

            if (!address.getAddressLineDetail().getLine2().isEmpty())
                sb.append(address.getAddressLineDetail().getLine2()).append("\n");
        }
        sb.append(address.getCityOrTown()).append("\n");
        if (address.getProvinceOrState() != null) {
            sb.append(address.getProvinceOrState().getName()).append("\n");
        }
        if (!address.getPostalCode().isEmpty()) {
            sb.append(address.getPostalCode()).append("\n");
        }
        if (address.getCountry() != null) {
            if (!address.getCountry().getName().isEmpty()) {
                sb.append(address.getCountry().getName()).append("\n");
            }
        }
        sb.append("....................\n");

        return sb.toString();
    }

    //pretty print all addresses
    public String prettyPrintAllAddresses(List<Address> addressList) {
        StringBuilder sb = new StringBuilder();
        for (Address address : addressList) {
            sb.append(prettyPrintAddress(address));
        }

        return sb.toString();

    }

    //Print address based on type
    public String printCertainTypeAddress(List<Address> addressList, String addressType) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Addresses of Type " + addressType + ":");
        for (Address address : addressList) {

            if (address.getType().getName().substring(0, address.getType().getName().indexOf(" ")).equals(addressType)) {
                sb.append(prettyPrintAddress(address));
            }
        }

        return sb.toString();

    }

    //Validate a given address
    public boolean isAddressValid(Address address) {

        boolean isValid = true;

        if (!StringUtils.isNumeric(address.getPostalCode())) {
            isValid = false;
        }
        if (address.getCountry() == null) {
            isValid = false;
        } else {

            if (address.getCountry().getCode().equals("ZA")) {
                if (address.getProvinceOrState() != null) {
                    if (StringUtils.isEmpty(address.getProvinceOrState().getName())) {
                        isValid = false;
                    }
                } else {
                    isValid = false;
                }

            }

        }

        if (address.getAddressLineDetail() != null) {
            if (StringUtils.isEmpty(address.getAddressLineDetail().getLine1()) && StringUtils.isEmpty(address.getAddressLineDetail().getLine2())) {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        return isValid;

    }

    //Validate all given addresses
    public String validateAllAddresses(List<Address> addressList) {

        StringBuilder message = new StringBuilder("");

        for (Address address : addressList) {
            message.append("Validating address " + address.getId()).append("\n");


            message.append(validationMessages(address)).append("\n");


            message.append("--------------------------------\n");
        }

        return message.toString();

    }

    //validation messages
    public static String validationMessages(Address address) {
        String validationMessage = "";

        if (!StringUtils.isNumeric(address.getPostalCode())) {
            validationMessage = "Postal Code is not Numeric";
        }
        if (address.getCountry() == null) {
            validationMessage += "Country is mandatory\n";
        } else {

            if (address.getCountry().getCode().equals("ZA")) {
                if (address.getProvinceOrState() != null) {
                    if (StringUtils.isEmpty(address.getProvinceOrState().getName())) {
                        validationMessage += "Country ZA must have a Province\n";
                    }
                } else {
                    validationMessage += "Country with code ZA must have a province\n";
                }

            }

        }

        if (address.getAddressLineDetail() != null) {
            if (StringUtils.isEmpty(address.getAddressLineDetail().getLine1()) && StringUtils.isEmpty(address.getAddressLineDetail().getLine2())) {
                validationMessage += "Address Line cannot be empty\n";
            }
        } else {
            validationMessage += "Address Line cannot be empty\n";
        }

        return validationMessage;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", addressLineDetail=" + addressLineDetail +
                ", provinceOrState=" + provinceOrState +
                ", cityOrTown='" + cityOrTown + '\'' +
                ", country=" + country +
                ", postalCode='" + postalCode + '\'' +
                ", suburbOrDistrict='" + suburbOrDistrict + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
