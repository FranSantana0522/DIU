import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import ProductList from "./components/productslist"

function App() {
  return (
    <div className="container-fluid">
      <Router>
          <Switch>
            <Route exact path={["/","/products"]} component={ProductList}/>
          </Switch>
      </Router>
    </div>
  );
}

export default App;
