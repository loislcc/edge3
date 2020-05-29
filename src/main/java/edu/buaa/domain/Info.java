package edu.buaa.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Info.
 */
@Entity
@Table(name = "info")
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Size(max = 255)
    @Column(name = "file_type", length = 255)
    private String fileType;

    @Lob
    @Column(name = "file_body")
    private byte[] fileBody;

    @Column(name = "file_body_content_type")
    private String fileBodyContentType;

    @Size(max = 300)
    @Column(name = "note", length = 300)
    private String note;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return fileName;
    }

    public Info file_name(String file_name) {
        this.fileName = file_name;
        return this;
    }

    public void setFile_name(String file_name) {
        this.fileName = file_name;
    }

    public Long getFile_size() {
        return fileSize;
    }

    public Info file_size(Long file_size) {
        this.fileSize = file_size;
        return this;
    }

    public void setFile_size(Long file_size) {
        this.fileSize = file_size;
    }

    public String getFile_type() {
        return fileType;
    }

    public Info file_type(String file_type) {
        this.fileType = file_type;
        return this;
    }

    public void setFile_type(String file_type) {
        this.fileType = file_type;
    }

    public byte[] getFile_body() {
        return fileBody;
    }

    public Info file_body(byte[] file_body) {
        this.fileBody = file_body;
        return this;
    }

    public void setFile_body(byte[] file_body) {
        this.fileBody = file_body;
    }

    public String getFile_bodyContentType() {
        return fileBodyContentType;
    }

    public Info file_bodyContentType(String file_bodyContentType) {
        this.fileBodyContentType = file_bodyContentType;
        return this;
    }

    public void setFile_bodyContentType(String file_bodyContentType) {
        this.fileBodyContentType = file_bodyContentType;
    }

    public String getNote() {
        return note;
    }

    public Info note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        if (info.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), info.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Info{" +
            "id=" + getId() +
            ", file_name='" + getFile_name() + "'" +
            ", file_size=" + getFile_size() +
            ", file_type='" + getFile_type() + "'" +
            ", file_body='" + getFile_body() + "'" +
            ", file_bodyContentType='" + getFile_bodyContentType() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
