const webpack = require("webpack");
const merge = require("webpack-merge");
const baseConfig = require("./webpack.base");
const UglifyJSPlugin = require("uglifyjs-webpack-plugin");
const ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = merge(baseConfig, {
    plugins: [
        new UglifyJSPlugin({
            test: /\.js($|\?)/i,
            cache: true,
            sourceMap: false
        })
    ]
});