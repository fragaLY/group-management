const React = require('react');
const DocumentTitle = require('react-document-title');
const i18n = require('internationalization');

import {withRouter} from 'react-router';

const AddressesByIdPage = withRouter(
    React.createClass({
        render() {
            const id = this.props.params.id;

            return (
                <DocumentTitle title={i18n('Address by id «{id}»', {id})}>
                    <Addresses id={id} fetched="false"/>
                </DocumentTitle>
            );
        }
    })
);

export default AddressesByIdPage;
