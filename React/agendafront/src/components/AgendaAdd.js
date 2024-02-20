import React, { Component } from 'react';
import { Link } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";

class AgendaAdd extends Component {
    constructor(props) {
        super(props);
        this.takeId=this.takeId.bind(this);
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

    takeId(e){
        const id = e.target.value;
        console.log(id)
        this.setState({
          id: id
        });
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
        const fech = e.target.value;
        console.log(fech)
        this.setState({
          fechaNacimiento: fech
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
    addAgenda(){
        AgendaDataService.create(this.state).then(response => { 
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
    render() { 
        return (  
            <div className='container-fluid mt-2'>
                <h4 className='form.label'>Añadir Contacto</h4>
                <div className="mb-3">
                    <label for="id" className="form-label">ID</label>
                    <input type="text" className="form-control" id="id" placeholder="Introduce id" onChange={this.takeId}/>
                </div>
                <div className="mb-3">
                    <label for="nombre" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="nombre" placeholder="Introduce nombre" onChange={this.takeName}/>
                </div>
                <div className="mb-3">
                    <label for="apellidos" className="form-label">Apellidos</label>
                    <input type="text" className="form-control" id="apellidos" placeholder="Introduce apellidos" onChange={this.takeApellidos}/>
                </div>
                <div className="mb-3">
                    <label for="fechaNacimiento" className="form-label">Fecha Nacimiento</label>
                    <input type="text" className="form-control" id="fechaNacimiento" placeholder="Introduce fecha nacimiento" onChange={this.takeFecha}/>
                </div>
                <div className="mb-3">
                    <label for="direccion" className="form-label">Direccion</label>
                    <input type="text" className="form-control" id="direccion" placeholder="Introduce direccion" onChange={this.takeDireccion}/>
                </div>
                <div className="mb-3">
                    <label for="localidad" className="form-label">Localidad</label>
                    <input type="text" className="form-control" id="localidad" placeholder="Introduce localidad" onChange={this.takeLocalidad}/>
                </div>
                <div className='mb-3'>
                <button className="btn btn-success" onClick={this.addAgenda}>Aceptar</button>
              </div>
            </div>
        );
    }
}
 
export default AgendaAdd;