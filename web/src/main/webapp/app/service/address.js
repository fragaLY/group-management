const Request = require('./request');

class AddressService {

    getAddresses() {
        const request = new Request();
        return request.get('addresses');
    }

    getAddresses(_id) {
        const request = new Request();
        return request.get('addresses/{id}', {id: _id});
    }

    createAddress(address) {
        const request = new Request();
        return request.put('addresses', {}, address);
    }

    updateAddress(address) {
        const request = new Request();
        return request.post('addresses', {}, address);
    }

    deleteAddress(address) {
        const request = new Request();
        return request.delete('addresses', {}, address);
    }

    deleteAddress(_id) {
        const request = new Request();
        return request.delete('addresses/{id}', {id: _id});
    }

}

export {AddressService};