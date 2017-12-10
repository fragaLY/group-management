import {t} from 'localizify';
import Address from './address';

const React = require('react');
const ReactDOM = require('react-dom');

class AddressList extends React.Component {



    render() {
        let addresses = this.props.addresses.map(address =>
            <Address key={address.links.href} address={address}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>{t('Country')}</th>
                    <th>{t('City')}</th>
                    <th>{t('Street')}</th>
                    <th>{t('Home')}</th>
                    <th>{t('Apartment Number')}</th>
                </tr>
                {addresses}
                </tbody>
            </table>
        )
    }
}
export default AddressList;
