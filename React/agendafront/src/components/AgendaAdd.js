import React, { Component } from 'react';
import { Link, Redirect } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";

class AgendaAdd extends Component {
    constructor(props) {
        super(props);
        this.onChangeValue=this.onChangeValue.bind(this);
        this.addAgenda=this.addAgenda.bind(this);
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
    onChangeValue = (e) => {
        const nameValue = e.target.name;
        const formValue = e.target.value;
        this.setState({[nameValue]: formValue},()=>{this.check()})
    }
    check = ()  =>{
        let id = this.state.id
        let nombre = this.state.nombre
        let apellidos = this.state.apellidos
        let fecha = this.state.fechaNacimiento
        let direccion = this.state.direccion
        let localidad = this.state.localidad
        let contador = 0
        if(!isNaN(id)){
            this.setState({
                idError: false
            })
        }else{
            this.setState({
                idError: true
            })
            contador++
        }
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
    addAgenda(){
        AgendaDataService.create(this.state).then(response => { 
          console.log(response.data);
          this.setState({ redirect: true });
        })
        .catch(e => {
          console.log(e);
        });
    }
    render() { 
        if (this.state.redirect) {
            return <Redirect to='/' />; 
        }
        return (  
            <div className='container-fluid mt-2 colorUser border border-5 border-dark shadow-lg rounded-4'>
                <h4 className='form.label'>Añadir Contacto</h4>
                <div className="mb-3">
                    <label for="id" className={`form-label ${this.state.idError?"text-danger":""}`}>ID {this.state.idError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="id" placeholder="Introduce id" onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="nombre" className={`form-label ${this.state.nameError?"text-danger":""}`}>Nombre {this.state.nameError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="nombre" placeholder="Introduce nombre" onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="apellidos" className={`form-label ${this.state.apellidosError?"text-danger":""}`}>Apellidos {this.state.apellidosError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="apellidos" placeholder="Introduce apellidos" onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="fechaNacimiento" className={`form-label ${this.state.fechaError?"text-danger":""}`}>Fecha Nacimiento {this.state.fechaError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="fechaNacimiento" placeholder="Introduce fecha nacimiento" onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="direccion" className={`form-label ${this.state.direccionError?"text-danger":""}`}>Direccion {this.state.direccionError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="direccion" placeholder="Introduce direccion" onChange={this.onChangeValue}/>
                </div>
                <div className="mb-3">
                    <label for="localidad" className={`form-label ${this.state.localidadError?"text-danger":""}`}>Localidad {this.state.localidadError?"Incorrecto":""}</label>
                    <input type="text" className="form-control" name="localidad" placeholder="Introduce localidad" onChange={this.onChangeValue}/>
                </div>
                <div className='mb-3'>
                <button className={`btn btn-success ${this.state.correct && this.props.tamaño<50 ? "" : "disabled"}`} onClick={this.addAgenda}>Aceptar</button>
              </div>
            </div>
        );
    }
}
 
export default AgendaAdd;