const merge = require("webpack-merge");
const baseConfig = require("./webpack.config.js");

module.exports = merge(baseConfig, {
    devtool: "source-map",

    devServer: {
        host: "localhost",
        port: "8080",
        contentBase: __dirname + "./build"
    },
});