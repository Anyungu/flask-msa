import unittest
import json
from app.test.base import BaseTestCase


def register_user(self):
    return self.client.post('/api/v1/user/',
                            data=json.dumps(
                                dict(email='example@gmail.com',
                                     username='username',
                                     password='123456')),
                            content_type='application/json')


def login_user(self):
    return self.client.post('/api/v1/auth/login',
                            data=json.dumps(
                                dict(email='example@gmail.com',
                                     password='123456')),
                            content_type='application/json')


class TestAuthBlueprint(BaseTestCase):
    def test_registered_user_login(self):
        """ Test for login of registered-user login """
        with self.client:
            # user registration
            user_response = register_user(self)
            print(1)
            print(user_response)
            response_data = json.loads(user_response.data.decode())

            self.assertTrue(response_data['Authorization'])
            self.assertEqual(user_response.status_code, 201)

            # registered user login
            login_response = login_user(self)
            print(2)
            print(login_response)
            data = json.loads(login_response.data.decode())

            self.assertTrue(data['Authorization'])
            self.assertEqual(login_response.status_code, 200)

    def test_valid_logout(self):
        """ Test for logout before token expires """
        with self.client:
            # user registration
            user_response = register_user(self)
            response_data = json.loads(user_response.data.decode())
            print(3)
            print(response_data)
            self.assertTrue(response_data['Authorization'])
            self.assertEqual(user_response.status_code, 201)

            # registered user login
            login_response = login_user(self)
            data = json.loads(login_response.data.decode())
            print(4)
            print(data)
            self.assertTrue(data['Authorization'])
            self.assertEqual(login_response.status_code, 200)

            # valid token logout
            response = self.client.post(
                '/api/v1/auth/logout',
                headers=dict(
                    Authorization='Bearer ' +
                    json.loads(login_response.data.decode())['Authorization']))
            data = json.loads(response.data.decode())
            print(4)
            print(data)
            self.assertTrue(data['status'] == 'success')
            self.assertEqual(response.status_code, 200)


if __name__ == '__main__':
    unittest.main()
