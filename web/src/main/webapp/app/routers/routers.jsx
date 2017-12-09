const React = require('react');
let {IndexRoute, Route} = require('react-router');
const App = require('../components/app.jsx');

import {AddressByIdPage, AddressesPage, HomePage} from '../components/index.jsx';

export default (
    <Route path="/" component={App}>
        <IndexRoute component={HomePage}/>
        <Route path="/addresses" component={AddressesPage}/>
        <Route path="/addresses/:id" component={AddressByIdPage}/>
    </Route>
);
