import React, { Component } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import FormC from './components/FormC';
import TablaC from './components/TablaC';

class App extends Component {
  constructor() {
    super()
    this.state = {
      data: [], //Contiene todas las filas de la tabla
      artist: '', //Contenido caja de texto userID
      song: '', //Contenido caja de texto Title
      lyrics: ''
    }
  }

  passParams= (data) => {
    let dataNew = this.state.data; //let declara variables de ámbito local
    dataNew.push(data) //El método push anexa nuevos elementos a un array
    this.setState({
      data: dataNew
    });
  }

  render() {
    return (
      <Container>
        <Row>
          <Col>
            <FormC passParams={this.passParams} />
          </Col>
        </Row>
        <Row>
          <Col>
            <TablaC data={this.state.data}/>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;
