import React, { Component } from 'react';
import { Form, Button, Row, Col, FormGroup } from 'react-bootstrap';

class FormC extends Component {

    constructor() {
        super()
        this.state = {
            artist: '',
            song: '',
        }
    }
    //Método que gestiona cuando se produce un cambio en algún campo del formualario
    //Obtiene la info del evento y actualiza el estado
    //La actualización del estado, implica la llamada al método render()
    handleChange = event => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({ [name]: value });
    }
    //Método que gestiona la pulsación del botón
    handleSubmit = event => {
        event.preventDefault();//paramos la ejecución por  del evento por defecto. 
        //Esto de utiliza para que no se proceda con el submit hasta asegurarnos de que se han enviado los datos correctamente.
        //Por ejemplo: Si un formulario tenemos un checkBox y no queremos que se envie el
        //formulario hasta que se seleccione ese checkBox. Podemos cancelar el evento si no está seleccionado 
        fetch(`https://api.lyrics.ovh/v1/${this.state.artist}/${this.state.song}`)
    .then((response) => {
        if (response.ok) {
            return response.json()
        } else {
            throw new Error(response.statusText);
        }
    }).then(data =>{
        this.props.passParams({
            artist: this.state.artist,
            song: this.state.song,
            lyric: data.lyrics
        })
        console.log(data.lyrics)
    })
    .catch(error => {
        console.error('Error fetching lyrics:', error);
    });

    }

//Una Promesa es un proxy para un valor no necesariamente conocido en el momento que es creada la promesa.
//Permite asociar manejadores que actuarán asincrónicamente sobre un eventual valor en caso de éxito, o la razón de falla en caso de una falla. 
//Esto permite que métodos asíncronos devuelvan valores como si fueran síncronos: en vez de inmediatamente retornar el valor final, el método asíncrono devuelve una promesa de suministrar el valor en algún momento en el futuro.

    render() {
        return (
        <Form onSubmit={this.handleSubmit}>
            <Row>
                <Col>
                    <Form.Group>
                        <Form.Label>Artist</Form.Label>
                        <Form.Control required placeholder="Introduce nombre artista/grupo" name="artist" value={this.state.artist} onChange={this.handleChange} />
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group>
                        <Form.Label>Song</Form.Label>
                        <Form.Control required placeholder="Introduzca cancion" name="song" value={this.state.song} onChange={this.handleChange} />
                    </Form.Group>
                </Col>
            </Row>
            <Row>
                <FormGroup>
                    <Button type="submit" >Add </Button>
                </FormGroup>
            </Row>
        </Form>)
    }

}

export default FormC;

/******************************************** Funciones con flecha JavaScript ES6  **********************************************************

//Observa, paso a paso, la descomposición de una "función tradicional" hasta la "función flecha" más simple:
//Nota: Cada paso a lo largo del camino es una "función flecha" válida

// Función tradicional
function (a){
  return a + 100;
}

// Desglose de la función flecha

// 1. Elimina la palabra "function" y coloca la flecha entre el argumento y el corchete de apertura.
(a) => {
  return a + 100;
}

// 2. Quita los corchetes del cuerpo y la palabra "return" — el return está implícito.
(a) => a + 100;

// 3. Suprime los paréntesis de los argumentos
a => a + 100;


*********************************************************************************************************************************************/

/******************************************** Funciones Rest Parameter JavaScript ES6   **********************************************************
Function Rest Parameter
The rest parameter (...) allows a function to treat an indefinite number of arguments as an array:

Example
function sum(...args) {
  let sum = 0;
  for (let arg of args) sum += arg;
  return sum;
}

let x = sum(4, 9, 16, 25, 29, 100, 66, 77);

*********************************************************************************************************************************************/
