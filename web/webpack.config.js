const webpack = require("webpack");
const path = require("path");

module.exports = {
    entry: "./src/main/js/app.jsx",
    devtool: "eval",
    cache: true,
    debug: true,
    watch: true,
    output: {
        path: __dirname + "/js",
        filename: "./bundle.js",
        library: "index"
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.jsx$/,
                exclude: /node_modules/,
                enforce: "pre",
                loader: "eslint-loader",
            },
            {
                test: /\.jsx$/,
                exclude: /node_modules/,
                loader: "babel-loader",
                options: {
                    cacheDirectory: true,
                    presets: ["es2015", "react"]
                }
            },
            {
                test: /\.hbs$/,
                loader: "handlebars"
            },
            {
                test: /\.(jpg|png|svg)$/,
                loader: "url-loader"
            },
            {
                test: /\.(jpg|png|svg)$/,
                loader: "file-loader",
                options: {
                    name: "[path][name].[ext]",
                },
            },
            {
                test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "url-loader?limit=10000&mimetype=application/font-woff"
            },
            {
                test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "file-loader"
            },
            {
                test: /\.less$/,
                use: [{
                    loader: "style-loader"
                }, {
                    loader: "css-loader"
                }, {
                    loader: "less-loader"
                }]
            }
        ]
    }
};