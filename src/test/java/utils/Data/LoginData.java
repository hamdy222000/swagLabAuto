package utils.Data;

import utils.filesManage.ReadJsonFile;

public class LoginData {

    public static final String LoginUrl = ReadJsonFile.getKey("pages", "login_data.url");
    public static final String ValidUsername = ReadJsonFile.getKey("pages", "login_data.validUsername");
    public static final String InvalidUsername = ReadJsonFile.getKey("pages", "login_data.invalidUsername");
    public static final String ValidPassword = ReadJsonFile.getKey("pages", "login_data.validPassword");
    public static final String InvalidPassword = ReadJsonFile.getKey("pages", "login_data.invalidPassword");
    public static final String BlankUsernameErrorMessage = ReadJsonFile.getKey("pages", "login_data.blankUsernameErrorMessage");
    public static final String BlankPasswordErrorMessage = ReadJsonFile.getKey("pages", "login_data.blankPasswordErrorMessage");
    public static final String InvalidUserErrorMessage = ReadJsonFile.getKey("pages", "login_data.invalidUserErrorMessage");




}
