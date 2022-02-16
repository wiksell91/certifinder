import {useState, useEffect} from 'react'
import { Layout, Menu, Table } from 'antd';
import { UploadOutlined, UserOutlined, VideoCameraOutlined } from '@ant-design/icons';
import {getAllCert, gettAllCertusers} from "./client";
import './App.css';

const { Header, Content, Footer, Sider } = Layout;

// Columns/table
const columns = [
    {
        title: 'Namn',
        dataIndex: ['certuser','name'],
        key: 'name',
    },
    {
        title: 'Ålder',
        dataIndex: ['certuser','age'],
        key: 'age',
    },
    {
        title: 'Stad',
        dataIndex: ['certuser','city'],
        key: 'city',
    },
    {
        title: 'Mail',
        dataIndex: ['certuser','email'],
        key: 'email',
    },
    {
        title: 'Behörighet',
        dataIndex: ['certificate','certType'],
        key: 'cert_type',
    },
    {
        title: 'Giltig t.o.m',
        dataIndex: 'validto',
        key: 'validto',
    },

];

function App() {
  const [certificatestatus, setCertificatestatus] = useState([]);

  const fetchCertificatestatus = () =>
      getAllCert()
          .then(res => res.json())
          .then(data => {
              console.log(data);
              setCertificatestatus(data);
          })

  useEffect(() => {
      console.log("hej hej hallå");
      fetchCertificatestatus();
  },[]);

  const renderCertificatestatus = () => {
      if(certificatestatus.length <= 0){
          return "Finns inget i systemet"
      }
      return <Table dataSource={certificatestatus}columns={columns} />;
  }

//Mall
  return <Layout>
      <Sider
          breakpoint="lg"
          collapsedWidth="0"
          onBreakpoint={broken => {
              console.log(broken);
          }}
          onCollapse={(collapsed, type) => {
              console.log(collapsed, type);
          }}
      >
          <div className="logo" />
          <Menu theme="dark" mode="inline" defaultSelectedKeys={['4']}>
              <Menu.Item key="1" icon={<UserOutlined />}>
                  nav 1
              </Menu.Item>
              <Menu.Item key="2" icon={<VideoCameraOutlined />}>
                  nav 2
              </Menu.Item>
              <Menu.Item key="3" icon={<UploadOutlined />}>
                  nav 3
              </Menu.Item>
              <Menu.Item key="4" icon={<UserOutlined />}>
                  nav 4
              </Menu.Item>
          </Menu>
      </Sider>
      <Layout>
          <Header className="site-layout-sub-header-background" style={{ padding: 0 }} />
          <Content style={{ margin: '24px 16px 0' }}>
              <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
                  {renderCertificatestatus()}
              </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
      </Layout>
  </Layout>


    //Mall slut
}

export default App;
