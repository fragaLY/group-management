const React = require('react');
const ReactDOM = require('react-dom');
const createHistory = require('history');
const i18n = require('internationalization');
let {Router, useRouterHistory} = require('react-router');

import en from './messages/en.json';
import ru from './messages/ru.json';

import routes from 'routers/routers.jsx';

i18n
    .add('en', en)
    .add('ru', ru)
    .setLocale(localStorage.locale || 'en');

const browserHistory = useRouterHistory(createHistory)({
    basename: window.config.basename
});

ReactDOM.render(
    <Router routes={routes} history={browserHistory}/>,
    document.getElementById('container')
);
