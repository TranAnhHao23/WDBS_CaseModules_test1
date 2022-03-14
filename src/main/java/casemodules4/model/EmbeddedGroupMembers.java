package casemodules4.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmbeddedGroupMembers implements Serializable {

    @Column(name = "idGroup")
    private Long idGroup;

    @Column(name = "idUser")
    private Long idUser;

}
