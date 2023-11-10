package clases.hotel.gestionhotel.modelo;

import java.time.LocalDate;
import java.util.Date;

public class ReservaVO {
    private Integer idVO;
    private LocalDate fechaLlegadaVO;
    private LocalDate fechaFinVO;
    private Integer numHabitacionVO;
    private String tipHabVO;
    private Boolean fumadorVO;
    private String regAlojVO;
    private String DNICliente;

    public ReservaVO( LocalDate fechaLlegadaVO, LocalDate fechaFinVO, Integer numHabitacionVO, String tipHabVO, Boolean fumadorVO, String regAlojVO, String DNICliente) {
        this.idVO =0;
        this.fechaLlegadaVO = fechaLlegadaVO;
        this.fechaFinVO = fechaFinVO;
        this.numHabitacionVO = numHabitacionVO;
        this.tipHabVO = tipHabVO;
        this.fumadorVO = fumadorVO;
        this.regAlojVO = regAlojVO;
        this.DNICliente = DNICliente;
    }

    public ReservaVO() {
    }

    public String getDNICliente() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente = DNICliente;
    }

    public Integer getIdVO() {
        return idVO;
    }

    public void setIdVO(Integer idVO) {
        this.idVO = idVO;
    }

    public LocalDate getFechaLlegadaVO() {
        return fechaLlegadaVO;
    }

    public void setFechaLlegadaVO(LocalDate fechaLlegadaVO) {
        this.fechaLlegadaVO = fechaLlegadaVO;
    }

    public LocalDate getFechaFinVO() {
        return fechaFinVO;
    }

    public void setFechaFinVO(LocalDate fechaFinVO) {
        this.fechaFinVO = fechaFinVO;
    }

    public Integer getNumHabitacionVO() {
        return numHabitacionVO;
    }

    public void setNumHabitacionVO(Integer numHabitacionVO) {
        this.numHabitacionVO = numHabitacionVO;
    }

    public String getTipHabVO() {
        return tipHabVO;
    }

    public void setTipHabVO(String tipHabVO) {
        this.tipHabVO = tipHabVO;
    }

    public Boolean getFumadorVO() {
        return fumadorVO;
    }

    public void setFumadorVO(Boolean fumadorVO) {
        this.fumadorVO = fumadorVO;
    }

    public String getRegAlojVO() {
        return regAlojVO;
    }

    public void setRegAlojVO(String regAlojVO) {
        this.regAlojVO = regAlojVO;
    }
}
