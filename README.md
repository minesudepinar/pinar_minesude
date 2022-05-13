# User login system MySQL JavaFx    

How to install 

MySQL must be installed
jdk version 17 must be installed

Go to "DatabaseConnect" class and change the information below, use your own MySQL username and password
private static String user_name = "root";
private static String password = "123456";

Go "HelloApplication" class and run main method.


How to use

You must create an accout first. Go to sign up page and create an account. Write the email, phone number and adress. You go to the exit. You can login with your account information.


More detail about code
DatabaseConnect section provides database connection and " private static String user_name = "root"; private static String password = "123456";" includes. This will vary depending on the user's MySQL account.
static public void addUser(String username, String password) throws Exception{
        PreparedStatement pstmt = connection.prepareStatement("insert into users (name,password) values(?,?)");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.executeUpdate();

        logIn(username,password);
User is added with this section.

static public User logIn(String username, String password) throws Exception{
        PreparedStatement pstmt = connection.prepareStatement("select * from users where name = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()){
            user = new User(resultSet.getInt("id"),resultSet.getString("name"));
        }

        loadUserData(user);

        return user;
    }
This section is used for the user's login.
User information is preserved with saveUserInfo. Also, user information is updated with updateUserInfo.

The application is started with the HelloApplication section.
With the HelloController section, it controls signup, username, password and warning.
The SignupController section controls the sign ups. identifies errors.
The user section gets the user information. (name, email, address, phone)
The UserInfoController section controls the user information (name, email, adress, phone) and exit, save, warnings.
