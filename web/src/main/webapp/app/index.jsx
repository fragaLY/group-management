const React = require('react');
const ReactDOM = require('react-dom');
const createHistory = require('history');
import {t} from 'localizify';
import en from './messages/en.json';
import ru from './messages/ru.json';

import routes from 'routers/routers.jsx';

let {Router, useRouterHistory} = require('react-router');

t
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
