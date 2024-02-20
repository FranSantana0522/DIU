import React, { Component } from 'react';
import { Link } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";


class AgendaEdit extends Component {
    constructor(props){
        super(props)
        this.takeName=this.takeName.bind(this);
        this.takeApellidos=this.takeApellidos.bind(this);
        this.takeFecha=this.takeFecha.bind(this);
        this.takeDireccion=this.takeDireccion.bind(this);
        this.takeLocalidad=this.takeLocalidad.bind(this);

        this.state = {
            id: '',
            nombre:'',
            apellidos:'',
            fechaNacimiento:'',
            direccion:'',
            localidad:''
        };
    }
    componentDidMount(){
        const agenda = this.props.match.params
        this.setState({
          id: agenda.id,
          nombre: agenda.nombre,
          apellidos: agenda.apellidos,
          fechaNacimiento: agenda.fechaNacimiento,
          direccion: agenda.direccion,
          localidad: agenda.localidad
        });  
        console.log(agenda)
    }
    takeName(e){
        const name = e.target.value;
        console.log(name)
        this.setState({
            nombre: name
        });
    }
    takeApellidos(e){
        const apell = e.target.value;
        console.log(apell)
        this.setState({
            apellidos: apell
        });
    }
    takeFecha(e){
        const naci = e.target.value;
        console.log(naci)
        this.setState({
            fechaNacimiento: naci
        });
    }
    takeDireccion(e){
        const direc = e.target.value;
        console.log(direc)
        this.setState({
            direccion: direc
        });
    }
    takeLocalidad(e){
        const loca = e.target.value;
        console.log(loca)
        this.setState({
            localidad: loca
        });
    }
    editAgenda(){
        AgendaDataService.update(this.state.id,this.state).then(response => { 
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }

    render(){
        return(
            <div className='container-fluid'>
                <h4 className='form.label'>Editar Contacto</h4>
                <div className="mb-3">
                    <label for="id" className="form-label">ID</label>
                    <input type="text" className="form-control" id="id" placeholder="" value={this.state.id} readOnly="true"/>
                </div>
                <div className="mb-3">
                    <label for="nombre" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="nombre" placeholder="" value={this.state.nombre} onChange={this.takeName}/>
                </div>
                <div className="mb-3">
                    <label for="apellidos" className="form-label">Apellidos</label>
                    <input type="text" className="form-control" id="apellidos" placeholder="" value={this.state.apellidos} onChange={this.takeApellidos}/>
                </div>
                <div className="mb-3">
                    <label for="fechaNacimiento" className="form-label">Fecha Nacimiento</label>
                    <input type="text" className="form-control" id="fechaNacimiento" placeholder="" value={this.state.fechaNacimiento} onChange={this.takeFecha}/>
                </div>
                <div className="mb-3">
                    <label for="direccion" className="form-label">Direccion</label>
                    <input type="text" className="form-control" id="direccion" placeholder="" value={this.state.direccion} onChange={this.takeDireccion}/>
                </div>
                <div className="mb-3">
                    <label for="localidad" className="form-label">Localidad</label>
                    <input type="text" className="form-control" id="localidad" placeholder="" value={this.state.localidad} onChange={this.takeLocalidad}/>
                </div>
                <div className='mb-3'>
                <button className="btn btn-success" onClick={this.editAgenda}>Aceptar</button>
              </div>
            </div>
        );
    }
}

export default AgendaEdit;