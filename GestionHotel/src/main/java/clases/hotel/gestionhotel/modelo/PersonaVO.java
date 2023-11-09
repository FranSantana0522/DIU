package clases.hotel.gestionhotel.modelo;

public class PersonaVO {
    private String DNIVO;
    private String nombreVO;
    private String apellidosVO;
    private String direccionVO;
    private String localidadVO;
    private String provinciaVO;

    public PersonaVO(String DNIVO, String nombreVO, String apellidosVO, String direccionVO, String localidadVO, String provinciaVO) {
        this.DNIVO = DNIVO;
        this.nombreVO = nombreVO;
        this.apellidosVO = apellidosVO;
        this.direccionVO = direccionVO;
        this.localidadVO = localidadVO;
        this.provinciaVO = provinciaVO;
    }

    public PersonaVO() {
    }

    public String getDNIVO() {
        return DNIVO;
    }

    public void setDNIVO(String DNIVO) {
        this.DNIVO = DNIVO;
    }

    public String getNombreVO() {
        return nombreVO;
    }

    public void setNombreVO(String nombreVO) {
        this.nombreVO = nombreVO;
    }

    public String getApellidosVO() {
        return apellidosVO;
    }

    public void setApellidosVO(String apellidosVO) {
        this.apellidosVO = apellidosVO;
    }

    public String getDireccionVO() {
        return direccionVO;
    }

    public void setDireccionVO(String direccionVO) {
        this.direccionVO = direccionVO;
    }

    public String getLocalidadVO() {
        return localidadVO;
    }

    public void setLocalidadVO(String localidadVO) {
        this.localidadVO = localidadVO;
    }

    public String getProvinciaVO() {
        return provinciaVO;
    }

    public void setProvinciaVO(String provinciaVO) {
        this.provinciaVO = provinciaVO;
    }
}
