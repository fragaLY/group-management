const webpack = require("webpack");
const path = require("path");

module.exports = {
    entry: "./app/index.jsx",
    devtool: "eval",
    cache: true,
    debug: true,
    watch: true,
    output: {
        path: path.join(__dirname, './resources/js'),
        filename: 'bundle.js',
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
                exclude: /(node_modules|bower_components|public)/,
                loader: "babel-loader",
                options: {
                    cacheDirectory: true,
                    presets: [
                        ["env", {
                            "targets": {
                                "browsers": ["last 2 versions"]
                            },
                            "debug": true
                        }]
                    ]
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
            },
            {
                test: /\.json$/,
                loader: 'json-loader'
            }
        ]
    },
    resolve: {
        modulesDirectories: ['node_modules'],
        root: path.resolve('./app'),
        extensions: ['', '.js', '.jsx'],
    },
    plugins: [
        // Avoid publishing files when compilation fails
        new webpack.NoErrorsPlugin(),
        new webpack.ProvidePlugin({
            '$': 'jquery',
            'jQuery': 'jquery',
            'window.jQuery': 'jquery',
        })
    ],
};