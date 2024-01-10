import { useState } from 'react';
import Button from './components/Button';
import './App.css';
import { create, all } from 'mathjs';

const math = create(all);

function App() {
  const[pantalla, setPantalla] = useState('0');
  const [cont, setCont] = useState(0);

  const borrarTodo = () =>{
      setPantalla('0');
      setCont(0)
  };

  const caracteres = (caracter) => {
    let exp = pantalla === '0' ? caracter : pantalla + caracter;
    setPantalla(exp);
  };

  const borrarUno = () => {
    pantalla.length === 1 ? setPantalla('0') : setPantalla(pantalla.slice(0, -1));
    if(pantalla.length===1){
      setCont(0)
    }
  };

  const parentesis = () => {
    const parentesis = ['(', ')'];
    let exp = pantalla === '0' ? parentesis[cont] : pantalla + parentesis[cont];
    setCont(cont === 1 ? 0 : 1);
    setPantalla(exp);
  };

  const igual = () =>{
    try {
      let resultado = math.evaluate(pantalla);
      setPantalla(resultado);
    } catch (error) {
      console.error(error);
      setPantalla('Syntax Error');
    }
  };
  return (
    <div className="App">
      <header className="App-header">
        <div className='calculadora'>
        <div className="pantalla"><h2>{pantalla}</h2></div>
        <div className="botones">
          <Button caracter='C' setPantalla={borrarTodo}></Button>
          <Button caracter='<-' setPantalla={borrarUno}></Button>
          <Button caracter='%' setPantalla={caracteres}></Button>
          <Button caracter='/' setPantalla={caracteres}></Button>
        </div>
        <div className="botones">
          <Button caracter='7' setPantalla={caracteres}></Button>
          <Button caracter='8' setPantalla={caracteres}></Button>
          <Button caracter='9' setPantalla={caracteres}></Button>
          <Button caracter='*' setPantalla={caracteres}></Button>
        </div>
        <div className="botones">
          <Button caracter='4' setPantalla={caracteres}></Button>
          <Button caracter='5' setPantalla={caracteres}></Button>
          <Button caracter='6' setPantalla={caracteres}></Button>
          <Button caracter='-' setPantalla={caracteres}></Button>
        </div>
        <div className="botones">
          <Button caracter='1' setPantalla={caracteres}></Button>
          <Button caracter='2' setPantalla={caracteres}></Button>
          <Button caracter='3' setPantalla={caracteres}></Button>
          <Button caracter='+' setPantalla={caracteres}></Button>
        </div>
        <div className="botones">
          <Button caracter='()'setPantalla={parentesis}></Button> 
          <Button caracter='0' setPantalla={caracteres}></Button>
          <Button caracter='. ' setPantalla={caracteres}></Button>
          <Button caracter='=' setPantalla={igual}></Button>
        </div>
        </div>
      </header>
    </div>
  );
}
export default App;
