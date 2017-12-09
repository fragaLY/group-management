const React = require('react');
const Header = require('./layout/header.jsx');
const Footer = require('./layout/footer.jsx');

class App extends React.Component {

    render() {
        return (
            <div>
                <Header/>
                <section className="content" id="main">
                    {this.props.children}
                </section>
                <Footer/>
            </div>
        );
    }
}

export default App;
