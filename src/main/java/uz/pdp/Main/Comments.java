package uz.pdp.Main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
