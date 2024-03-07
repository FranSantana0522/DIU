import React from 'react';
import '../App.css'; // Importa tus estilos CSS personalizados aquí
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa los estilos CSS de Bootstrap
import '../css/styles.css';
import {useState} from "react";
import { Link,useHistory } from "react-router-dom";
import { getAuth, signInWithEmailAndPassword } from "firebase/auth"
import app from '../firebase';


const AgendaLogin = ({setLogeado }) => {
    setLogeado(false);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const history = useHistory(); 

    const signInWithEmailAndPasswordHandler = (event,email, password) => {
        event.preventDefault();
        signInWithEmailAndPassword(getAuth(), email, password)
            .then(() => {
              setLogeado(true);
              history.push("/agenda");
            })
            .catch(error => {
                setError("Error signing in with password and email!");
                console.error("Error signing in with password and email", error);
            });
      };
      
      const onChangeHandler = (event) => {
          const {name, value} = event.currentTarget;
        
          if(name === 'userEmail') {
              setEmail(value);
          }
          else if(name === 'userPassword'){
            setPassword(value);
          }
      };
   

  return (
    <div className="mt-8 d-flex flex-column">
      <h1 className="text-3xl mb-2 text-center font-bold">Iniciar sesion</h1>
      <div className="border border-blue-400 mx-auto w-11/12 md:w-2/4 rounded py-8 px-4 md:px-8">
        {error !== null && <div className = "py-4 bg-danger  w-full text-center mb-3">{error}</div>}
        <form className="d-flex flex-column">
          <label htmlFor="userEmail" className="block">
            Email:
          </label>
          <input
            type="email"
            className="my-1 p-1 w-full"
            name="userEmail"
            value = {email}
            placeholder="E.g: prueba@gmail.com"
            id="userEmail"
            onChange = {(event) => onChangeHandler(event)}
          />
          <label htmlFor="userPassword" className="block">
            Contraseña:
          </label>
          <input
            type="password"
            className="mt-1 mb-3 p-1 w-full"
            name="userPassword"
            value = {password}
            placeholder="Your Password"
            id="userPassword"
            onChange = {(event) => onChangeHandler(event)}
          />
          <button className="bg-green-400 hover:bg-green-500 w-full py-2" onClick = {(event) => {signInWithEmailAndPasswordHandler(event, email, password)}}>
            Iniciar sesion
          </button>
        </form>
      </div>
    </div>
  );
};

export default AgendaLogin;