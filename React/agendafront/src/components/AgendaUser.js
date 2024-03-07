import React, { useContext } from 'react';
import { Link, Redirect } from "react-router-dom";
import AgendaDataService from "../services/agenda.service.js";
import fotoPerfil from '../images/fotoPerfil.jpg';
import UserProvider, { UserContext } from "../provider/UserProvider";

const AgendaUser = () => {

    const user = useContext(UserContext);
    const {photoURL, displayName, email} = user;
    console.log(user);

        return ( 
            <UserProvider>
            <div className='shadow-lg border border-5 border-dark rounded-4 d-flex align-items-center colorUser'>
                <input className='form-control form-control-sm w-50 ms-5 mr-2' readOnly="true" value={displayName != null ? displayName : "Usuario"}/>
                <img className='img-fluid rounded-5 ms-5' src={photoURL != null ? photoURL : fotoPerfil} style={{height: '70vh'}} alt="Imagen usuario" />
            </div>
            </UserProvider>
         );
    
};
 
export default AgendaUser;