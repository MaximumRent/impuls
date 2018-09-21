package com.megapapa.impulse.broadcaster;

import com.megapapa.impulse.entity.cayenne.IndividualTask;
import com.megapapa.impulse.entity.cayenne.Role;
import com.megapapa.impulse.entity.cayenne.User;
import org.junit.Test;

import java.util.Map;

public class FromCayenneToDTO {

    @Test
    public void testCayenneToDTO() {
        Role role = new Role();
        role.setName("admin");
        User user = new User();
        user.setEmail("email");
        user.setPasswordHash("ASDF");
        user.setPasswordSalt("IIIIIEIEIEIE");
        user.writePropertyDirectly("role", role);
        IndividualTask itask = new IndividualTask();
        itask.setName("nuame");
        itask.setDescription("desc");
        itask.writePropertyDirectly("user", user);
        Broadcaster broadcaster = new Broadcaster();
        broadcaster
                .include("name")
                .include("description")
                .include("endLimit")
                .include("user")
                .maxDepth(3);
//        System.out.println(broadcaster.translate(itask));

        Map<String, Object> jsonMap = broadcaster.translate(itask, 0);
        jsonMap.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }
}
