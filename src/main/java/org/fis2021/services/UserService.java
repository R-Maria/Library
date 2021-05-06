package org.fis2021.services;

import javafx.scene.control.SingleSelectionModel;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.exceptions.*;
import org.fis2021.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class UserService {
    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("library.db").toFile())
                .openOrCreate("test", "test");
        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role, String name, String email, String phoneNumber) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, email, phoneNumber));
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static boolean checkUserAlreadyExist(String username, String password, String role) throws PasswordIncorrectException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                if (!Objects.equals(user.getPassword(), encodePassword(username, password)) || !Objects.equals(role, user.getRole()))
                    throw new PasswordIncorrectException(password);
                else
                    return true;
            }
        }
        return false;
    }

    public static void checkPhoneNumber(String number) throws WrongPhoneNumberException {
        if (!number.matches("^[0]+[7]+\\d{8}"))
            throw new WrongPhoneNumberException();
    }

    public static void checkEmail(String email) throws WrongEmailException {
        if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"))
            throw new WrongEmailException();
    }

    public static void checkFieldsLogin(String username, String password, SingleSelectionModel<String> role) throws EmptyFieldException {
        if (username.isEmpty() || password.isEmpty() || role.isEmpty())
            throw new EmptyFieldException();
    }

    public static void checkFieldsRegister(String username, String password, SingleSelectionModel<String> role, String name, String phone, String email) throws EmptyFieldException {
        if (username.isEmpty() || password.isEmpty() || role.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty())
            throw new EmptyFieldException();
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8).replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
