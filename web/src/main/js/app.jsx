'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {addresses: []};
    }

    componentWillMount() {
        client({method: 'GET', path: '/addresses'}).done(response => {
            this.setState({addresses: response.entity._embedded.addresses});
        });
    }

    render() {
        return (
            <AddressList addresses={this.state.addresses}/>
        )
    }
}

class AddressList extends React.Component {
    render() {
        let addresses = this.props.addresses.map(address =>
            <Address key={address.links.href} address={address}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Country</th>
                    <th>City</th>
                    <th>Street</th>
                    <th>Home</th>
                    <th>Apartment Number</th>
                </tr>
                {addresses}
                </tbody>
            </table>
        )
    }
}

class Address extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.address.country}</td>
                <td>{this.props.address.city}</td>
                <td>{this.props.address.street}</td>
                <td>{this.props.address.home}</td>
                <td>{this.props.address.apartmentNumber}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('container')
);