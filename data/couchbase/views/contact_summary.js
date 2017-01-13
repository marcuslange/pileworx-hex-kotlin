function (doc, meta) {
    emit(meta.id, {
        "id":doc.id.value,
        "name":doc.name.last + ", " + doc.name.first,
        "email":doc.email.localPart + "@" + doc.email.domain,
        "state":doc.address.state
    });
}