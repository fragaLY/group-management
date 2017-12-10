import {Link} from "react-router";
import {t} from 'localizify';

const React = require('react');
const ReactDOM = require('react-dom');
const Link = require('react-router');

class Address extends React.Component {

    render() {

        return (
            <tr>
                <td>{this.props.address.country}</td>
                <td>{this.props.address.city}</td>
                <td>{this.props.address.street}</td>
                <td>{this.props.address.home}</td>
                <td>{this.props.address.apartmentNumber}</td>
                <td>
                    <Link to={`/addresses/${id}`}>{t('Update')}
                    </Link>
                </td>
                <td>
                    <Link to={`/addresses/${id}`}>{t('Delete')}
                    </Link>
                </td>
            </tr>
        )
    }
}

export default Address;