package Entity;

import java.io.Serializable;
import java.util.Objects;

public class TbBrandEntity implements Serializable {
    private int id;
    private String brandName;
    private String companyName;
    private Integer ordered;
    private String description;
    private Integer status;

    public TbBrandEntity() {
    }

    public TbBrandEntity(String brandName, String companyName, Integer ordered, String description, Integer status) {
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    public TbBrandEntity(int id, String brandName, String companyName, Integer ordered, String description, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbBrandEntity that = (TbBrandEntity) o;
        return id == that.id && Objects.equals(brandName, that.brandName) && Objects.equals(companyName, that.companyName) && Objects.equals(ordered, that.ordered) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, companyName, ordered, description, status);
    }
    @Override
    public String toString() {
        return "TbBrandEntity{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
