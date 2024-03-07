import React, { Component } from 'react';
import { Link, withRouter } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";
import agendaService from '../services/agenda.service.js';


class AgendaEdit extends Component {
    constructor(props){
        super(props)
        this.onChangeValue=this.onChangeValue.bind(this);
        this.editAgenda=this.editAgenda.bind(this);
        this.removeAgenda=this.removeAgenda.bind(this);
        this.check=this.check.bind(this);

        this.state = {
            id: 0,
            nombre:'',
            apellidos:'',
            fechaNacimiento:'',
            direccion:'',
            localidad:'',
            redirect:false,
            correct:false,
            idError:false,
            nameError:false,
            apellidosError:false,
            fechaError:false,
            direccionError:false,
            localidadError:false
        };
    }
    componentDidMount() {
        const agenda = this.props.location.state.agenda;
        this.setState({
            id: agenda.id,
            nombre: agenda.nombre,
            apellidos: agenda.apellidos,
            fechaNacimiento: agenda.fechaNacimiento,
            direccion: agenda.direccion,
            localidad: agenda.localidad
        });
        console.log(agenda)
        this.props.setLogeado(true);
    }
    onChangeValue = (e) => {
        const nameValue = e.target.name;
        const formValue = e.target.value;
        this.setState({[nameValue]: formValue},()=>{this.check()})
    }
    check = ()  =>{
        let nombre = this.state.nombre
        let apellidos = this.state.apellidos
        let fecha = this.state.fechaNacimiento
        let direccion = this.state.direccion
        let localidad = this.state.localidad
        let contador = 0
        if(nombre.length > 0 ){
            this.setState({
                nameError: false
            })   
        } else{
            this.setState({
                nameError: true
            })
            contador++
        }
        if(apellidos.length > 0 ){
            this.setState({
                apellidosError: false
            })
        } else{
            this.setState({
                apellidosError: true
            })
            contador++
        }
        if(Date.parse(fecha) && fecha.length==10){
            this.setState({
                fechaError: false
            })
        } else{
            this.setState({
                fechaError: true
            })
            contador++
        }
        if(direccion.startsWith("C/") ){
            this.setState({
                direccionError: false
            })
        } else{
            this.setState({
                direccionError: true
            })
            contador++
        }
        if(localidad.length > 0 ){
            this.setState({
                localidadError: false
            })
        } else{
            this.setState({
                localidadError: true
            })
            contador++
        }
        if(contador > 0){
            this.setState({
                correct: false
            })
        } else{
            this.setState({
                correct: true
            }) 
        }
    }
    editAgenda(){
        AgendaDataService.update(this.state.id,this.state).then(response => { 
          console.log(response.data);
          this.props.history.push("/", { logeado: this.props.logeado });
        })
        .catch(e => {
          console.log(e);
        });
    }
    removeAgenda() {
        const confirmDelete = window.confirm("¿Estás seguro de que deseas borrar este contacto?");
        if (confirmDelete) {
            AgendaDataService.delete(this.state.id)
                .then(response => {
                    console.log(response.data);
                    this.props.history.push("/", { logeado: this.props.logeado });
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }
    render(){
        
        return(  
            <div className='container-fluid colorUser colorUser border border-5 border-dark shadow-lg rounded-4'>
                <h4 className='form.label'>Editar Contacto</h4>
                <div className="mb-3">
                    <label for="id" className="form-label">ID</label>
                    <input type="text" className="form-control" id="id" placeholder="" value={this.state.id} readOnly="true"/>
                </div>
                <div className="mb-3">
                    <label for="nombre" className={`form-label ${this.state.nameError?"text-danger":""}`}>Nombre {this.state.nameError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="nombre" placeholder="" value={this.state.nombre} onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="apellidos" className={`form-label ${this.state.apellidosError?"text-danger":""}`}>Apellidos {this.state.apellidosError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="apellidos" placeholder="" value={this.state.apellidos} onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="fechaNacimiento" className={`form-label ${this.state.fechaError?"text-danger":""}`}>Fecha Nacimiento {this.state.fechaError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="fechaNacimiento" placeholder="" value={this.state.fechaNacimiento} onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="direccion" className={`form-label ${this.state.direccionError?"text-danger":""}`}>Direccion {this.state.direccionError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="direccion" placeholder="" value={this.state.direccion} onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="localidad" className={`form-label ${this.state.localidadError?"text-danger":""}`}>Localidad {this.state.localidadError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="localidad" placeholder="" value={this.state.localidad} onChange={this.onChangeValue}/>
                </div>
                <div className='mb-3'>
                <button className={`btn btn-success ${this.state.correct ? "" : "disabled"}`} onClick={this.editAgenda}>Aceptar</button>
                <button className="btn btn-danger ms-5" onClick={this.removeAgenda}>Borrar</button>
              </div>
            </div>
        );
    }
}

export default AgendaEdit;