import React from 'react';
import './App.css'; // Importa tus estilos CSS personalizados aquí
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa los estilos CSS de Bootstrap
import fotoPerfil from './images/fotoPerfil.jpg';
import './css/styles.css';
import {useState, useEffect, useContext} from "react";
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import { generateUserDocument } from './firebase';
import UserProvider, { UserContext } from "./provider/UserProvider";

import AgendaList from './components/AgendaList';
import AgendaEdit from './components/AgendaEdit';
import AgendaAdd from './components/AgendaAdd';
import AgendaUser from './components/AgendaUser';
import AgendaLogin from './components/AgendaLogin';

function App() {
  const [logeado,setLogeado]=useState(false);
  useEffect(() => {
    // Aquí puedes poner cualquier lógica que quieras ejecutar cuando `logeado` cambie
    console.log('El estado logeado ha cambiado:', logeado);
  }, [logeado]);


    const user = useContext(UserContext);
    const {photoURL, displayName, email} = user;
  
  
  return (
    <UserProvider>
    <Router>
    {/*Administrador añadir editar borrar*/}
    <div className='container-fluid color'>
        
      <div className='navbar navbar-expand-lg navbar-light bg-dark row'>
        <div className="col-3">
          <Link to={"/agenda"} className="navbar-brand text-light">
            Agenda
          </Link>
        </div>
        <div className="col-3">   
        {logeado ?
        <Link to={"/add"} className="navbar-brand text-light">
            Añadir persona
          </Link> 
          : 
          <Link to={"/login"} className="navbar-brand text-light">
            Añadir persona
          </Link>
        }
        </div>
        <div className="col-3 d-flex align-items-center">    
        <Link to={"/user"} className="navbar-brand text-light">
            {displayName!= null ? displayName: "Usuario"}
          <img src={photoURL!=null ?photoURL:fotoPerfil} alt="Perfil" className="img-fluid rounded-5 ms-2" style={{ maxHeight: '3vh' }}/>     
        </Link>
        </div>
        <div className="col-3">
          {logeado ?
        <Link to={"/login"} className="navbar-brand text-light">
            Cerrar sesion
          </Link>
          :
          <Link to={"/login"} className="navbar-brand text-light">
            Iniciar sesion
          </Link>
          }
        </div>
      </div>

      {/*En otro componente tabla Switch*/}
      <div className="container-fluid mt-3">
    
      <Switch>
          {/*El en switch se renderizarán todas los compoentes cuta URL coicidan con la activa*/}
          <Route exact path={["/", "/agenda"]} render={(props) => <AgendaList {...props} logeado={logeado} />} />
          {<Route exact path="/add" component={AgendaAdd} />}
          { <Route path="/agenda/:id" render={(props) => <AgendaEdit {...props} logeado={logeado} setLogeado={setLogeado} />} />}
            <Route exact path="/user" component={AgendaUser}/>
            <Route exact path="/login" render={(props) => <AgendaLogin {...props} setLogeado={setLogeado} />} />
      </Switch>
    
      </div>
      
    </div>
    </Router>
    </UserProvider>
  );
}

export default App;
