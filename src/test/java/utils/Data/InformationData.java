package utils.Data;

import utils.filesManage.ReadJsonFile;

public class InformationData {
    public static final String InformationUrl = ReadJsonFile.getKey("pages", "information_data.url");

    public static final String ValidFirstName = ReadJsonFile.getKey("pages", "information_data.firstName.valid");
    public static final String SpCharFirstName = ReadJsonFile.getKey("pages", "information_data.firstName.spChar");
    public static final String NoFirstName = ReadJsonFile.getKey("pages", "information_data.firstName.number");

    public static final String ValidLastName = ReadJsonFile.getKey("pages", "information_data.lastName.valid");
    public static final String SpCharLastName = ReadJsonFile.getKey("pages", "information_data.lastName.spChar");
    public static final String NoLastName = ReadJsonFile.getKey("pages", "information_data.lastName.number");

    public static final String ValidPostalCode = ReadJsonFile.getKey("pages", "information_data.postalCode.valid");
    public static final String SpCharPostalCode = ReadJsonFile.getKey("pages", "information_data.postalCode.spChar");
    public static final String CharPostalCode = ReadJsonFile.getKey("pages", "information_data.postalCode.char");

    public static final String BlankFirstNameErrorMessage = ReadJsonFile.getKey("pages", "information_data.blankFirstNameErrorMessage");
    public static final String BlankLastNameErrorMessage = ReadJsonFile.getKey("pages", "information_data.blankLastNameErrorMessage");
    public static final String BlankPostalCodeErrorMessage = ReadJsonFile.getKey("pages", "information_data.blankPostalCodeErrorMessage");

}
