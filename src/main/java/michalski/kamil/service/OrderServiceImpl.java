package michalski.kamil.service;


import michalski.kamil.dao.ProductDao;
import michalski.kamil.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductDao productDao;

    @Override
    public void processOrder(long productId, int count) {
        Product productById = productDao.findOne(productId);
        if (productById.getUnitsInStock() < count) {
            throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba " +
                    "sztuk w magazynie: " + productById.getUnitsInStock());

        }
        productById.setUnitsInStock(productById.getUnitsInStock() - count);
        productDao.save(productById);
    }

}
