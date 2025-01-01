package mdx


import java.io.Serializable;

public class UserDto implements Serializable{
    private static final long serialVersionUID = 1L;
    Long id
    String username

    public UserDto(AppUser user){
        username = user.username;
        id = user.id;
    }

    public UserDto(Long id, String username){
        this.id = id;
        this.username = username
    }

}
