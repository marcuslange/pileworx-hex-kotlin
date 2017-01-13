function (doc, meta) {
    emit(meta.id, {
        "id": doc.id.value,
        "name": doc.name,
        "address": doc.address,
        "phone": {
            "one": doc.phone.areaCode,
            "two": doc.phone.localExchange,
            "three": doc.phone.stationNumber
        },
        "email": doc.email.localPart + "@" + doc.email.domain
    });
}