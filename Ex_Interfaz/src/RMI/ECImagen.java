/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferzo
 */
@Entity
@Table(name = "IMAGEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ECImagen.findAll", query = "SELECT e FROM ECImagen e")
    , @NamedQuery(name = "ECImagen.findByIdimagen", query = "SELECT e FROM ECImagen e WHERE e.idimagen = :idimagen")
    , @NamedQuery(name = "ECImagen.findByNombre", query = "SELECT e FROM ECImagen e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "ECImagen.findByUrl", query = "SELECT e FROM ECImagen e WHERE e.url = :url")
    , @NamedQuery(name = "ECImagen.findByFechadescarga", query = "SELECT e FROM ECImagen e WHERE e.fechadescarga = :fechadescarga")
    , @NamedQuery(name = "ECImagen.findByIdcliente", query = "SELECT e FROM ECImagen e WHERE e.idcliente = :idcliente")})
public class ECImagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDIMAGEN")
    private Integer idimagen;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "URL")
    private String url;
    @Column(name = "FECHADESCARGA")
    @Temporal(TemporalType.DATE)
    private Date fechadescarga;
    @Column(name = "IDCLIENTE")
    private Integer idcliente;

    public ECImagen() {
    }
    

    public ECImagen(Integer idimagen) {
        this.idimagen = idimagen;
    }

    public ECImagen(Integer idimagen, String nombre, String url) {
        this.idimagen = idimagen;
        this.nombre = nombre;
        this.url = url;
    }

    public Integer getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(Integer idimagen) {
        this.idimagen = idimagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFechadescarga() {
        return fechadescarga;
    }

    public void setFechadescarga(Date fechadescarga) {
        this.fechadescarga = fechadescarga;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimagen != null ? idimagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ECImagen)) {
            return false;
        }
        ECImagen other = (ECImagen) object;
        if ((this.idimagen == null && other.idimagen != null) || (this.idimagen != null && !this.idimagen.equals(other.idimagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RMI.ECImagen[ idimagen=" + idimagen + " ]";
    }
    
}
