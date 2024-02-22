import React, { Component } from 'react';
import { Link, Redirect } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";
import fotoPerfil from '../images/fotoPerfil.jpg';

class AgendaUser extends Component {
    constructor(props) {
        super(props);
    }

    render() { 
        return ( 
            <div className='shadow-lg border border-5 border-dark rounded-4 d-flex align-items-center colorUser'>
                <input className='form-control form-control-sm w-50 ms-5 mr-2' readOnly="true"/>
                <img className='img-fluid rounded-5 ms-5' src={fotoPerfil} style={{height: '70vh'}} alt="Imagen usuario" />
            </div>
         );
    }
}
 
export default AgendaUser;