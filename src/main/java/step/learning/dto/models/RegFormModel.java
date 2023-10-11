package step.learning.dto.models;

import org.apache.commons.fileupload.FileItem;
import step.learning.services.formparse.FormParseResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegFormModel {

    // region fields
    private String name ;
    private String login ;
    private String password ;
    private String repeat ;
    private String email ;
    private String avatar ;
    private Date birthdate ;
    private boolean isAgree ;
    // endregion


    public RegFormModel (FormParseResult result) throws ParseException
    {
        Map<String, String> fields = result.getFields();
        this.setName( fields.get( "reg-name" ) ) ;
        this.setLogin( fields.get( "reg-login" ) ) ;
        this.setPassword( fields.get( "reg-password" ) ) ;
        this.setRepeat( fields.get( "reg-repeat" ) ) ;
        this.setEmail( fields.get( "reg-email" ) ) ;
        this.setBirthdate( fields.get( "reg-birthdate" ) ) ;
        this.setIsAgree( fields.get( "reg-rules" ) ) ;
        Map<String, FileItem> files = result.getFiles();
        if(files.containsKey("reg-avatar"))
        {
            this.setAvatar(files.get("reg-avatar"));
        }
    }

    public Map<String, String> getErrorMessages(){
        Map<String, String> result = new HashMap<>();
        if( name == null || "".equals(name))
        {
            result.put("name", "Ім'я не може бути порожнім");
        }
        return result;
    }
    // region accessors
    private void setAvatar(FileItem item)
    {
        String submittedFilename = item.getName();
        String ext = submittedFilename.substring(submittedFilename.lastIndexOf('.'));
        String savedFilename;
        File savedFile;
        do{
            savedFilename = UUID.randomUUID().toString().substring(0,8) + ext;
            savedFile = new File("./" + savedFilename);
        }while(savedFile.exists());
        try{
            item.write(savedFile);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.avatar = savedFilename;
    }
    public String getAvatar()
    {
        return avatar;
    }
    public String getBirthdateAsString(){
        return formDateFormat.format(getBirthdate());
    }
    public void setBirthdate( String birthdate ) throws ParseException {
        this.birthdate = formDateFormat.parse( birthdate ) ;
    }
    public void setIsAgree( String isAgree ) {
        this.isAgree = "on".equalsIgnoreCase( isAgree ) ||
                "true".equalsIgnoreCase( isAgree ) ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }
    // endregion

    private static final SimpleDateFormat formDateFormat =
            new SimpleDateFormat("yyyy-MM-dd" ) ;
}
