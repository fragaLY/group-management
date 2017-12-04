'use strict';

let rest = require('rest');
let defaultRequest = require('rest/interceptor/defaultRequest');
let mime = require('rest/interceptor/mime');
let errorCode = require('rest/interceptor/errorCode');
let baseRegistry = require('rest/mime/registry');

let registry = baseRegistry.child();

registry.register('application/hal+json', require('rest/mime/type/application/hal'));

module.exports = rest
    .wrap(mime, {registry: registry})
    .wrap(errorCode)
    .wrap(defaultRequest, {headers: {'Accept': 'application/hal+json'}});
