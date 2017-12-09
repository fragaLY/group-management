const React = require('react');
const Link = require('react-router');
const i18n = require('localizify');

import LanguageSwitcher from '../utils/language-switcher';

const Header = React.createClass({
    render() {
        return (
            <header id="header">
                <div className="header-wrap">
                    <div className="header">
                        <ul id="menu" className="menu">
                            <li className="li"><Link to="/addresses"><u>{i18n('Addresses')}</u></Link></li>
                            <li className="li right"><LanguageSwitcher/></li>
                        </ul>
                    </div>
                </div>
            </header>
        );
    }
});

export default Header;