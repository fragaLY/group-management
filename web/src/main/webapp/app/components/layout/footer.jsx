const React = require('react');
const i18n = require('internationalization');

const Footer = React.createClass({
    render() {
        return (
            <footer className="footer" id="footer">
                <div className="copyright">
                    {i18n('Created by Vadzim Kavalkou')} <span className="js-now-year">2017</span>
                </div>
            </footer>
        );
    }
});

export default Footer;