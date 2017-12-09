const React = require('react');
const i18n = require('localizify');

const HomePage = React.createClass({
    render() {
        return (
            <div>
                {i18n('Hello')}
            </div>
        );
    }
});

export default HomePage;