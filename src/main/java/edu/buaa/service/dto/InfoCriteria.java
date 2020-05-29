package edu.buaa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Info entity. This class is used in InfoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /infos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class InfoCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter file_name;

    private LongFilter file_size;

    private StringFilter file_type;

    private StringFilter note;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFile_name() {
        return file_name;
    }

    public void setFile_name(StringFilter file_name) {
        this.file_name = file_name;
    }

    public LongFilter getFile_size() {
        return file_size;
    }

    public void setFile_size(LongFilter file_size) {
        this.file_size = file_size;
    }

    public StringFilter getFile_type() {
        return file_type;
    }

    public void setFile_type(StringFilter file_type) {
        this.file_type = file_type;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final InfoCriteria that = (InfoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(file_name, that.file_name) &&
            Objects.equals(file_size, that.file_size) &&
            Objects.equals(file_type, that.file_type) &&
            Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        file_name,
        file_size,
        file_type,
        note
        );
    }

    @Override
    public String toString() {
        return "InfoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (file_name != null ? "file_name=" + file_name + ", " : "") +
                (file_size != null ? "file_size=" + file_size + ", " : "") +
                (file_type != null ? "file_type=" + file_type + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
            "}";
    }

}
